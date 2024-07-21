package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.ChatRoomDTO;
import com.adkp.fuexchange.pojo.ChatRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ChatMessageMapper.class}
)
public interface ChatRoomMapper {

    @Mapping(source = "chatRoomId", target = "chatRoomId")
    @Mapping(source = "active", target = "active")
    @Mapping(source = "chatMessageId", target = "chatMessage")
    ChatRoomDTO toChatRoomDTO(ChatRoom chatRoom);

    List<ChatRoomDTO> toChatRoomDTOList(List<ChatRoom> chatMessageList);

}
