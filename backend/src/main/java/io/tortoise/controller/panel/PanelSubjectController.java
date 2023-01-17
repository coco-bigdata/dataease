package io.tortoise.controller.panel;

import io.tortoise.base.domain.PanelSubject;
import io.tortoise.controller.request.panel.PanelSubjectRequest;
import io.tortoise.service.panel.PanelSubjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: wangjiahao
 * Date: 2021-05-06
 * Description:
 */
@RestController
@RequestMapping("panel/subject")
public class PanelSubjectController {

    @Resource
    private PanelSubjectService panelSubjectService;

    @PostMapping("/query")
    public List<PanelSubject> query(@RequestBody PanelSubjectRequest request) {
        return panelSubjectService.query(request);
    }

    @PostMapping("/querySubjectWithGroup")
    public List<PanelSubject> querySubjectWithGroup(@RequestBody PanelSubjectRequest request) {
        return panelSubjectService.querySubjectWithGroup(request);
    }

    @PostMapping("/update")
    public void update(@RequestBody PanelSubjectRequest request) {
        panelSubjectService.update(request);
    }


    @DeleteMapping("/delete/{id}")
    public void update(@PathVariable String id) {
        panelSubjectService.delete(id);
    }


}
