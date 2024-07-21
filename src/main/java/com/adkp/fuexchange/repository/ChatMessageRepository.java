package com.adkp.fuexchange.repository;

import com.adkp.fuexchange.pojo.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, ChatMessage> {
}
