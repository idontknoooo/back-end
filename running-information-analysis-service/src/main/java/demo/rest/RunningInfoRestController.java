package demo.rest;

import demo.domain.RunningInfo;
import demo.service.RunningInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RunningInfoRestController {

    // Dependency Injection
    @Autowired
    private RunningInfoService runningInfoService;

    @RequestMapping(value = "runningInfo", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void postRunningInfo(@RequestBody List<RunningInfo> runningInfoList)
    {
        this.runningInfoService.saveRunningInfo(runningInfoList);
    }

//    http://localhost:8080/runningInfo?page=0&size=3
    @RequestMapping(value = "runningInfo", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<RunningInfo> findAllOrderByHealthWarningLevelDesc(@RequestParam("page") int page,
                                                                  @RequestParam(value = "size", defaultValue = "2") Integer size) {
        PageRequest request = new PageRequest(page, size, Sort.Direction.DESC, "healthWarningLevel");
        return this.runningInfoService.findAll(request);
    }

//    http://localhost:8080/runningInfo/07e8db69-99f2-4fe2-b65a-52fbbdf8c32c?page=0
    @RequestMapping(value = "runningInfo/{runningId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<RunningInfo> findAllByRunningIdOrderByHealthWarningLevelDesc(
                                                @RequestParam("page") int page,
                                                @RequestParam(value = "size", defaultValue = "2") Integer size,
                                                @PathVariable("runningId") String runningId) {
        PageRequest request = new PageRequest(page, size, Sort.Direction.DESC, "healthWarningLevel");
        return this.runningInfoService.findAllByRunningId(runningId, request);
    }

    @RequestMapping(value = "runningInfo", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll(){
        this.runningInfoService.deleteAll();
    }

    @RequestMapping(value = "runningInfo/{runningId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteByRunningId(@PathVariable("runningId") String runningId){
        this.runningInfoService.deleteByRunningId(runningId);
    }

}
