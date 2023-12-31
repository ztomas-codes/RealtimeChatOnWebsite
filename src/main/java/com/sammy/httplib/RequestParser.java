package com.sammy.httplib;

public class RequestParser {
    public static Request fromString(String raw) {
        Request request = new Request();

        boolean readingHeaders = false;
        boolean readingBody = false;
        boolean valid = false;

        for (String line : raw.split("\n")) {
            if (readingBody) {
                request.setBody(request.getBody() + line);
            } else if (readingHeaders) {
                if (line.isEmpty()) {
                    readingBody = true;
                    readingHeaders = false;
                } else {
                    request.addHeader(line);
                    valid = true;
                }
            } else {
                if (request.setHttpMeta(line)) {
                    readingHeaders = true;
                } else {
                    return null;
                }
            }
        }

        return valid ? request : null;
    }

    public static Request fromByteArray(byte[] bytes) {
        return fromString(new String(bytes));
    }
}
