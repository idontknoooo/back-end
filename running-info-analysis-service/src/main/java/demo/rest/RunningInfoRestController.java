package demo.rest;

import demo.domain.RunningInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import demo.service.RunningInfoService;
import java.util.List;

public @RestController class RunningInfoRestController {

    public static int sizeDefault = 2;

    @Autowired
    private RunningInfoService runningInfoService;

    // Post info to server
    @RequestMapping(value = "running-info", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED) // 201
    public void upload(@RequestBody List<RunningInfo> runningInformationList) {
        runningInfoService.postRunningInfo(runningInformationList);
    }


    // GET info by RunningID, DESC: healthWarningLevel
    @RequestMapping(value = "running-info/{running-id}", method = RequestMethod.GET)
    public Page<RunningInfo> findByRunningId(@RequestParam("page") int page,
                                               @RequestParam(value = "size", required = false) Integer size,
                                               @PathVariable("running-id") String running_id) {
        if(size==null) size=sizeDefault;
        PageRequest request = new PageRequest(page, size, Sort.Direction.DESC, "healthWarningLevel");
        return runningInfoService.findByRunningId(running_id, request);
    }

    // Get all info, DECS: healthWarningLevel
    @RequestMapping(value = "running-info", method = RequestMethod.GET)
    public Page<RunningInfo> findAllDescByHealthWarningLevel(@RequestParam("page") int page,
                                                                @RequestParam(value = "size", required = false) Integer size) {
        if(size==null) size=sizeDefault;
        PageRequest request = new PageRequest(page, size, Sort.Direction.DESC, "healthWarningLevel");
        return runningInfoService.findAll(request);
    }

    // Delete by RunningId
    @RequestMapping(value = "running-info/{running-id}", method = RequestMethod.DELETE)
    public void deleteByRunningId(@PathVariable("running-id") String running_id) {
        runningInfoService.deleteByRunningId(running_id);
    }

    // Delete All
    @RequestMapping(value = "running-info", method = RequestMethod.DELETE)
    public void deleteAll() {
        runningInfoService.deleteAll();
    }

    // Generate Entire Json Block
    private String getJsonBlock(Page<RunningInfo> pages) {
        List<RunningInfo> l = pages.getContent();
        StringBuilder res = new StringBuilder();
        res.append('{');
        for(int i = 0; i != l.size(); i++) {
            res.append(l.get(i).getJson());
            if(i != l.size()-1) res.append(",\n");
        }
        res.append('}');
        return res.toString();
    }
}