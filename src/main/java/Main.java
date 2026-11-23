import obj.Config;
import obj.Contact;
import obj.Railway;

import java.util.HashMap;
import java.util.Map;

import kieler_gen.Interface;

public class Main {
    static boolean eqColours(boolean[] c1, boolean[] c2) {
        return c1[0] == c2[0] && c1[1] == c2[1] && c1[2] == c2[2];
    }
    static void main() throws Exception {
        Railway conf = Config.getConfig().testTrack;
        Interface controller = new Interface(false);
        RailwayManager railwayManager = new RailwayManager(conf.simulationAddresses);

        HashMap<String, Integer> trackToIndexMap = new HashMap<>();
        for (int i = 0; i < conf.tracks.length; ++i) {
            trackToIndexMap.putIfAbsent(conf.tracks[i], i);
        }

        controller.reset();

        boolean[][][] oldSignals = controller.signals;
        boolean[] oldPoints = controller.points;
        boolean[] oldLights = controller.lights;
        int[] oldTrackSpeeds = controller.trackSpeeds;
        boolean oldCloseGate = controller.closeGate;

        // old* vars and controller should not have the same reference to the same output arrays.
        controller.reset();

        railwayManager.connect();

        while (!controller.isTerminated()) {
            try {
                // request new contact data for controller input
                for (Map.Entry<String, Contact[]> entry : railwayManager.requestContacts().entrySet()) {
                    Integer trackIndex = trackToIndexMap.get(entry.getKey());
                    if (trackIndex == null) { // Track has no contacts
                        continue;
                    }
                    int trackI = trackIndex;
                    Contact[] contacts = entry.getValue();

                    for (Contact contact : contacts) {
                        if (contact != null) {
                            controller.contacts[trackI][contact.number][0] = contact.type;
                            controller.contacts[trackI][contact.number][1] = contact.count;
                        }
                    }
                }

                // run controller
                controller.tick();

                // store old output so we can only send the changes to the rpies.
                for (int i = 0; i < controller.points.length; ++i) {
                    if (controller.points[i] != oldPoints[i]) {
                        railwayManager.setPoint("PNT_" + i, controller.points[i]);
                        oldPoints[i] = controller.points[i];
                    }
                }

                for (int i = 0; i < controller.lights.length; ++i) {
                    if (controller.lights[i] != oldLights[i]) {
                        railwayManager.setLight("L_" + i, controller.lights[i]);
                        oldLights[i] = controller.lights[i];
                    }
                }

                for (int i = 0; i < controller.trackSpeeds.length; ++i) {
                    int speed = controller.trackSpeeds[i];
                    if (speed != oldTrackSpeeds[i]) {
                        railwayManager.setTrack(conf.tracks[i], speed == 0 ? 0 : speed > 0 ? 1 : 2, speed);
                        oldTrackSpeeds[i] = controller.trackSpeeds[i];
                    }
                }

                for (int i = 0; i < controller.signals.length; ++i) {
                    boolean[][] trackSignals = controller.signals[i];
                    for (int j = 0; j < trackSignals.length; ++j) {
                        // if signal actually exists and the color has changed
                        if (trackSignals[j] != null && !eqColours(trackSignals[j], oldSignals[i][j])) {
                            railwayManager.setSignal(conf.tracks[i], j, trackSignals[j]);
                            oldSignals[i][j] = trackSignals[j].clone();
                        }
                    }
                }

                if (controller.closeGate != oldCloseGate) {
                    railwayManager.setBell("Bell_0", controller.closeGate);
                    railwayManager.setGate("Gate_0", controller.closeGate);
                    railwayManager.setGate("Gate_1", controller.closeGate);
                }
                oldCloseGate = controller.closeGate;

                // submit new railway State
                railwayManager.setRailway(true);
                Thread.sleep(Config.getConfig().sleepMs);

            } catch (Exception e) {
                System.err.println("FATAL ERROR: STOPPING RAILWAY");
                System.err.println(e);
                railwayManager.resetRailway();
                railwayManager.setRailway(true);
            }

        }

        railwayManager.resetRailway();
        railwayManager.disconnect(true, true);
    }
}