package com.syokinet.ipmanager.repository;

import com.syokinet.ipmanager.entity.IPAddress;
import com.syokinet.ipmanager.entity.IPAddressStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPAddressRepository extends JpaRepository<IPAddress, Long> {
    List<IPAddress> findByStatus(IPAddressStatus status);
    List<IPAddress> findByStatusAndIpBetween(IPAddressStatus status, Optional<String> startIp, Optional<String> endIp);

    Optional<IPAddress> findByIp(String ipAddress);

    IPAddress findFirstByStatus(IPAddressStatus status);

    @Transactional
    @Modifying
    @Query("update IPAddress ip set ip.status = ?1, ip.customer.id = ?2 where ip.ip = ?3")
    void updateByIp(IPAddressStatus status, Long customerId, String ipAddress);
}
