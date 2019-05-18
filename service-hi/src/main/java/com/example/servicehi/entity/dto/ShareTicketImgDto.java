package com.example.servicehi.entity.dto;

import com.example.servicehi.entity.ShareTicketImg;
import com.example.servicehi.entity.TipsRelation;
import com.example.servicehi.entity.UploadImg;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("ShareTicketImgDto")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ShareTicketImgDto extends ShareTicketImg {
    private List<TipsRelation> tipsRelations;
    private UploadImg uploadImg;
    private String tipsName;
}
