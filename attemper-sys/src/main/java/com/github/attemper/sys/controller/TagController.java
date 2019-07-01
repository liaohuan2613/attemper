package com.github.attemper.sys.controller;

import com.github.attemper.common.constant.APIPath;
import com.github.attemper.common.param.sys.tag.*;
import com.github.attemper.common.result.CommonResult;
import com.github.attemper.common.result.sys.tag.Tag;
import com.github.attemper.common.result.sys.tenant.Tenant;
import com.github.attemper.sys.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ldang
 */
@Api("Tag")
@RestController
public class TagController {
	
	@Autowired
	private TagService service;

	@ApiOperation("List tags")
	@ApiImplicitParam(value = "TagListParam", name = "param", dataType = "TagListParam", required = true)
	@GetMapping(APIPath.TagPath.$)
	public CommonResult<Map<String, Object>> list(TagListParam param) {
		return CommonResult.putResult(service.list(param));
	}

	@ApiOperation("Get tag")
	@ApiImplicitParam(value = "TagGetParam", name = "param", dataType = "TagGetParam", required = true)
	@GetMapping(APIPath.TagPath.GET)
	public CommonResult<Tag> get(TagGetParam param) {
		return CommonResult.putResult(service.get(param));
	}

	@ApiOperation("Add tag")
	@ApiImplicitParam(value = "TagSaveParam", name = "param", dataType = "TagSaveParam", required = true)
	@PostMapping(APIPath.TagPath.$)
	public CommonResult<Tag> add(@RequestBody TagSaveParam param) {
		return CommonResult.putResult(service.add(param));
	}

	@ApiOperation("Update tag")
	@ApiImplicitParam(value = "TagSaveParam", name = "param", dataType = "TagSaveParam", required = true)
	@PutMapping(APIPath.TagPath.$)
	public CommonResult<Tag> update(@RequestBody TagSaveParam param) {
		return CommonResult.putResult(service.update(param));
	}

	@ApiOperation("Remove tag")
	@ApiImplicitParam(value = "TagRemoveParam", name = "param", dataType = "TagRemoveParam", required = true)
	@DeleteMapping(APIPath.TagPath.$)
	public CommonResult<Void> remove(@RequestBody TagRemoveParam param) {
		return CommonResult.putResult(service.remove(param));
	}

	@ApiOperation("Get users of tag")
	@ApiImplicitParam(value = "TagGetParam", name = "param", dataType = "TagGetParam", required = true)
	@GetMapping(APIPath.TagPath.TENANT)
	public CommonResult<List<Tenant>> getTenants(TagGetParam param) {
		return CommonResult.putResult(service.getTenants(param));
	}

	@ApiOperation("Update users of tag")
	@ApiImplicitParam(value = "TagTenantSaveParam", name = "param", dataType = "TagTenantSaveParam", required = true)
	@PutMapping(APIPath.TagPath.TENANT)
	public CommonResult<Void> saveTenants(@RequestBody TagTenantSaveParam param) {
		return CommonResult.putResult(service.saveTenants(param));
    }

	@ApiOperation("Get resources of tag")
	@ApiImplicitParam(value = "TagGetParam", name = "param", dataType = "TagGetParam", required = true)
	@GetMapping(APIPath.TagPath.RESOURCE)
	public CommonResult<List<String>> getResources(TagGetParam param) {
		return CommonResult.putResult(service.getResources(param));
	}

	@ApiOperation("Update resources of tag")
	@ApiImplicitParam(value = "TagResourceSaveParam", name = "param", dataType = "TagResourceSaveParam", required = true)
	@PutMapping(APIPath.TagPath.RESOURCE)
	public CommonResult<Void> saveResources(@RequestBody TagResourceSaveParam param) {
		return CommonResult.putResult(service.saveResources(param));
	}
}
