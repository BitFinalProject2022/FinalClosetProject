package com.showmual.entity.closet;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ClosetRepository extends JpaRepository<ClosetEntity, Integer> {
    
    ClosetEntity findByitemId(int itemId);
}
