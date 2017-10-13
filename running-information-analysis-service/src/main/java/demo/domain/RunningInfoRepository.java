package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunningInfoRepository extends JpaRepository<RunningInfo, String>{

    Page<RunningInfo> findAllByRunningId(String runningId, Pageable pageable);
}
