package demo.service;

import demo.domain.RunningInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RunningInfoService{ //extends JpaRepository<RunningInfo, String>{

    List<RunningInfo> saveRunningInfo(List<RunningInfo> runningInfoList);
    Page<RunningInfo> findAll(Pageable pageable);
    Page<RunningInfo> findAllByRunningId(String runningId, Pageable pageable);
    void deleteAll();
    void deleteByRunningId(String runningId);

}
