package demo.service.impl;

import demo.domain.RunningInfoRepo;
import demo.domain.RunningInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import demo.service.RunningInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

public @Service class RunningInfoServiceImpl implements RunningInfoService {

    @Autowired
    private RunningInfoRepo runningInfoRepo;

    @Override
    public List<RunningInfo> postRunningInfo(List<RunningInfo> runningInformationList) {
        return this.runningInfoRepo.save(runningInformationList);
    }

    @Override
    public void deleteAll() {
        this.runningInfoRepo.deleteAll();
    }

    @Override
    public Page<RunningInfo> findAll(Pageable pageable) {
        return this.runningInfoRepo.findAll(pageable);
    }

    @Override
    public void deleteByRunningId(String runningId) {
        this.runningInfoRepo.delete(runningId);
    }

    @Override
    public Page<RunningInfo> findByRunningId(String runningId, Pageable pageable) {
        return this.runningInfoRepo.findByRunningId(runningId, pageable);
    }
}
