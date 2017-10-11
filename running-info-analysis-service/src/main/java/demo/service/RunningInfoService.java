package demo.service;

import demo.domain.RunningInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RunningInfoService {

    // Save runningInfo in a list
    List<RunningInfo> postRunningInfo(List<RunningInfo> runningInformationList);

    // Find by runningId
    Page<RunningInfo> findByRunningId(String runningId, Pageable pageable);

    // Find all records
    Page<RunningInfo> findAll(Pageable pageable);

    // Delete by runningId
    void deleteByRunningId(String runningId);

    // Delete all records
    void deleteAll();
}