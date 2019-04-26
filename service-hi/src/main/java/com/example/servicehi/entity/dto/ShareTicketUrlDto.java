package com.example.servicehi.entity.dto;

import com.example.servicehi.entity.ShareTicketUrl;
import com.example.servicehi.entity.TipsRelation;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("ShareTicketUrlDto")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ShareTicketUrlDto extends ShareTicketUrl {
    private List<TipsRelation> tipsRelations;
    private String tipsName;
}
