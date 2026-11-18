import obj.Config;
import obj.Contact;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import kieler_gen.Interface;

public class Main {
    static void main() throws Exception {
        Config conf = Config.getConfig();
        Interface controller = new Interface(false);
        RailwayManager railwayManager = new RailwayManager(conf.addresses.simulation.railway);

        HashMap<String, Integer> trackToIndexMap = new HashMap<>();
        for (int i = 0; i < conf.tracks.length; ++i) {
            trackToIndexMap.putIfAbsent(conf.tracks[i], i);
        }

        boolean[][][] oldSignals = controller.signals;
        boolean[] oldPoints = controller.points;
        boolean[] oldLights = controller.lights;
        int[] oldTrackSpeeds = controller.trackSpeeds;
        boolean oldOpenGate = controller.openGate;

        // old* vars and controller should not have the same refernce to the same output
        // arrays.
        controller.localReset();

        while (!controller.isFinal()) {
            try {
                for (Map.Entry<String, Contact[]> entry : railwayManager.requestContacts().entrySet()) {
                    int trackI = trackToIndexMap.get(entry.getKey());
                    Contact[] contacts = entry.getValue();

                    for (Contact contact : contacts) {
                        if (contact != null) {
                            controller.contacts[trackI][contact.number][0] = contact.type;
                            controller.contacts[trackI][contact.number][0] = contact.count;
                        }
                    }
                }

                controller.tick();

                for (int i = 0; i < controller.points.length; ++i) {
                    if (controller.points[i] != oldPoints[i]) {
                        railwayManager.setPoint("P_" + i, controller.points[i]);
                    }
                }
                oldPoints = controller.points;

                for (int i = 0; i < controller.lights.length; ++i) {
                    if (controller.lights[i] != oldLights[i]) {
                        railwayManager.setPoint("L_" + i, controller.lights[i]);
                    }
                }
                oldLights = controller.lights;

                for (int i = 0; i < controller.trackSpeeds.length; ++i) {
                    int speed = controller.trackSpeeds[i];
                    if (speed != oldTrackSpeeds[i]) {
                        railwayManager.setTrack(conf.tracks[i], speed == 0 ? 0 : speed > 0 ? 1 : 2, speed);
                    }
                }
                oldTrackSpeeds = controller.trackSpeeds;

                for (int i = 0; i < controller.signals.length; ++i) {
                    boolean[][] trackSignals = controller.signals[i];
                    for (int j = 0; j < trackSignals.length; ++j) {
                        if (trackSignals[j] != null) {
                            railwayManager.setSignal(conf.tracks[i], j, trackSignals[j]);
                        }
                    }
                    oldSignals = controller.signals;

                    if (controller.openGate != oldOpenGate) {
                        railwayManager.setBell("Bell_0", controller.openGate);
                        railwayManager.setGate("Gate_0", controller.openGate);
                        railwayManager.setGate("Gate_1", controller.openGate);
                    }
                    oldOpenGate = controller.openGate;

                }

                Thread.sleep(conf.sleepMs);

            } catch (Exception e) {
                System.err.println("FATAL ERROR: STOPPING RAILWAY");
                System.err.println(e);
                railwayManager.resetRailway();
                railwayManager.setRailway(true);
            }

        }
    }
}