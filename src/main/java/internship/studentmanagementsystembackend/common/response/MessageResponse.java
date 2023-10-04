package internship.studentmanagementsystembackend.common.response;

public record MessageResponse(
        String message,
        MessageType messageType
) { }