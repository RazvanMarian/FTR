package com.unibuc.FTR.repository;

import com.unibuc.FTR.entity.PlayerTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerTransferRepository extends JpaRepository<PlayerTransfer, Integer> {
}
