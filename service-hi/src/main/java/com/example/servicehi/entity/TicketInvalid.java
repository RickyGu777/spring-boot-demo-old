package com.example.servicehi.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("TicketInvalid")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TicketInvalid extends BaseEntity {
    private String ticketUUID;
}