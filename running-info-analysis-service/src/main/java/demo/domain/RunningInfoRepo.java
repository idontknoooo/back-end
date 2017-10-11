package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RunningInfoRepo extends JpaRepository<RunningInfo, String> {

    Page<RunningInfo> findByRunningId(String runningId, Pageable pageable);

}