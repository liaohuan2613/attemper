package com.github.attemper.web.controller;

import com.github.attemper.common.constant.APIPath;
import com.github.attemper.common.param.dispatch.instance.JobInstanceActParam;
import com.github.attemper.common.param.dispatch.instance.JobInstanceIdParam;
import com.github.attemper.common.param.dispatch.instance.JobInstanceListParam;
import com.github.attemper.common.result.CommonResult;
import com.github.attemper.common.result.dispatch.instance.JobInstanceAct;
import com.github.attemper.core.service.instance.JobInstanceService;
import com.github.attemper.web.service.JobInstanceOperatedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api("JobInstance")
@RestController
public class JobInstanceController {

    @Autowired
    private JobInstanceService jobInstanceService;

    @Autowired
    private JobInstanceOperatedService jobInstanceOperatedService;

    @ApiOperation("List job instances")
    @ApiImplicitParam(value = "JobInstanceListParam", name = "param", dataType = "JobInstanceListParam", required = true)
    @GetMapping(APIPath.InstancePath.$)
    public CommonResult<Map<String, Object>> list(JobInstanceListParam param) {
        return CommonResult.putResult(jobInstanceService.list(param));
    }

    @ApiOperation("List all activity nodes of one instance")
    @ApiImplicitParam(value = "JobInstanceActParam", name = "param", dataType = "JobInstanceActParam", required = true)
    @GetMapping(APIPath.InstancePath.LIST_ACT)
    public CommonResult<List<JobInstanceAct>> listAct(JobInstanceActParam param) {
        return CommonResult.putResult(jobInstanceService.listAct(param));
    }

    @ApiOperation("Retry")
    @ApiImplicitParam(value = "JobInstanceIdParam", name = "param", dataType = "JobInstanceIdParam", required = true)
    @PostMapping(value = APIPath.InstancePath.RETRY)
    public CommonResult<Void> retry(@RequestBody JobInstanceIdParam param) {
        return CommonResult.putResult(jobInstanceOperatedService.retry(param));
    }

    @ApiOperation("Terminate")
    @ApiImplicitParam(value = "JobInstanceIdParam", name = "param", dataType = "JobInstanceIdParam", required = true)
    @PostMapping(value = APIPath.InstancePath.TERMINATE)
    public CommonResult<Void> terminate(@RequestBody JobInstanceIdParam param) {
        return CommonResult.putResult(jobInstanceOperatedService.terminate(param));
    }

    @ApiOperation("Pause")
    @ApiImplicitParam(value = "JobInstanceIdParam", name = "param", dataType = "JobInstanceIdParam", required = true)
    @PostMapping(value = APIPath.InstancePath.PAUSE)
    public CommonResult<Void> pause(@RequestBody JobInstanceIdParam param) {
        return CommonResult.putResult(jobInstanceOperatedService.pause(param));
    }

    @ApiOperation("Activate")
    @ApiImplicitParam(value = "JobInstanceIdParam", name = "param", dataType = "JobInstanceIdParam", required = true)
    @PostMapping(value = APIPath.InstancePath.ACTIVATE)
    public CommonResult<Void> activate(@RequestBody JobInstanceIdParam param) {
        return CommonResult.putResult(jobInstanceOperatedService.activate(param));
    }
}
