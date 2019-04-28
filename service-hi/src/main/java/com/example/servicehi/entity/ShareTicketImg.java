package com.example.servicehi.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.Objects;

@Alias("ShareTicketImg")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ShareTicketImg extends BaseEntity{
    private String QRCode;
    private String uploadImgUUID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShareTicketImg that = (ShareTicketImg) o;
        return Objects.equals(QRCode, that.QRCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(QRCode);
    }
}