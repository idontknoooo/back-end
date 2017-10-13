package demo.service.impl;

import demo.domain.RunningInfo;
import demo.domain.RunningInfoRepository;
import demo.service.RunningInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunningInfoServiceImpl implements RunningInfoService {

    // Dependency Injection
    @Autowired
    private RunningInfoRepository runningInfoRepository;

    @Override
    public List<RunningInfo> saveRunningInfo(List<RunningInfo> runningInfoList) {
        return this.runningInfoRepository.save(runningInfoList);
    }

    @Override
    public Page<RunningInfo> findAll(Pageable pageable) {
        return this.runningInfoRepository.findAll(pageable);
    }

    @Override
    public Page<RunningInfo> findAllByRunningId(String runningId, Pageable pageable) {
        return this.runningInfoRepository.findAllByRunningId(runningId, pageable);
    }

    @Override
    public void deleteAll() {
        this.runningInfoRepository.deleteAll();
    }

    @Override
    public void deleteByRunningId(String runningId) {
        this.runningInfoRepository.delete(runningId);
    }
}
