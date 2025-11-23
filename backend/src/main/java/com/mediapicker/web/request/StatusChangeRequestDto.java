package com.mediapicker.web.request;

import com.mediapicker.domain.mediathek.medium.Status;

import java.util.UUID;

public record StatusChangeRequestDto(UUID mediumId, Status status) {
}
