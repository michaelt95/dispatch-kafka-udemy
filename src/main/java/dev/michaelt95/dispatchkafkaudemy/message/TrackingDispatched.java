package dev.michaelt95.dispatchkafkaudemy.message;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrackingDispatched {
    UUID orderId;
    String status;
}
