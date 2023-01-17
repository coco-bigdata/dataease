package io.tortoise.controller.panel;

import io.tortoise.base.domain.PanelTemplateWithBLOBs;
import io.tortoise.controller.handler.annotation.I18n;
import io.tortoise.controller.request.panel.PanelTemplateRequest;
import io.tortoise.dto.panel.PanelTemplateDTO;
import io.tortoise.service.panel.PanelTemplateService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: wangjiahao
 * Date: 2021-03-05
 * Description:
 */
@RestController
@RequestMapping("template")
public class PanelTemplateController {

    @Resource
    private PanelTemplateService panelTemplateService;

    @PostMapping("/templateList")
    @I18n
    public List<PanelTemplateDTO> templateList(@RequestBody PanelTemplateRequest request) {
        return panelTemplateService.templateList(request);
    }

    @PostMapping("/save")
    public PanelTemplateDTO save(@RequestBody PanelTemplateRequest request) {
        return panelTemplateService.save(request);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        panelTemplateService.delete(id);
    }

    @GetMapping("/findOne/{id}")
    public PanelTemplateWithBLOBs findOne(@PathVariable String id) throws Exception {
        return panelTemplateService.findOne(id);
    }

    @PostMapping("/find")
    public List<PanelTemplateDTO> find(@RequestBody PanelTemplateRequest request) {
        return panelTemplateService.find(request);
    }


    @PostMapping("/nameCheck")
    public String nameCheck(@RequestBody PanelTemplateRequest request) {
        return panelTemplateService.nameCheck(request);
    }


}
