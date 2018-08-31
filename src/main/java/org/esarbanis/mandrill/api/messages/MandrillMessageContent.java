package org.esarbanis.mandrill.api.messages;

import java.util.List;
import java.util.Map;

/**
 * <p>The content of a sent message.  Corresponds to the result of {@code messages/content.json}.</p>
 *
 */
public class MandrillMessageContent {
    private Long ts;
    private String _id;
    private String from_email, from_name, subject;
    private MandrillMessage.Recipient to;
    private List<String> tags;
    private Map< String, String > headers;
    private String text, html;
    private List< MandrillMessage.MessageContent > attachments;

    /**
     * @return The Unix timestamp from when this message was sent
     */
    public Long getTs() {
        return ts;
    }

    /**
     * @return The message's unique id
     */
    public String get_id() {
        return _id;
    }

    /**
     * @return The email address of the sender
     */
    public String getFrom_email() {
        return from_email;
    }

    /**
     * @return the alias of the sender (if any)
     */
    public String getFrom_name() {
        return from_name;
    }

    /**
     * @return the message's subject line
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @return The message recipient's information
     */
    public MandrillMessage.Recipient getTo() {
        return to;
    }

    /**
     * @return List of tags on this message
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * @return The text part of the message, if any
     */
    public String getText() {
        return text;
    }

    /**
     * @return The HTML part of the message, if any
     */
    public String getHtml() {
        return html;
    }

    /**
     * @return A list of any attachments that can be found in the message
     */
    public List<MandrillMessage.MessageContent> getAttachments() {
        return attachments;
    }

    /**
     * @return The key-value pairs of the custom MIME headers for the message's main document
     */
    public Map<String, String> getHeaders() {
        return headers;
    }
}
