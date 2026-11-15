package protocol;

import obj.Contact;

public final class ContactsResponse extends ContactsRequest {
    public final Contact[] contacts;

    private ContactsResponse(Contact[] contacts) {
        super();
        this.contacts = contacts;
    }
}
