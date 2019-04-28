package com.example.servicehi.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.Objects;

@Alias("ShareTicketUrl")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ShareTicketUrl extends BaseEntity{
    private String url;
    private String title;
    private String remark;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShareTicketUrl that = (ShareTicketUrl) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}