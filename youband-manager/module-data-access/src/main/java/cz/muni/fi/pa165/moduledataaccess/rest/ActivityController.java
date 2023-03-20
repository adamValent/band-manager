package cz.muni.fi.pa165.moduledataaccess.rest;

import cz.muni.fi.pa165.moduledataaccess.model.Activity;
import cz.muni.fi.pa165.moduledataaccess.model.dto.ActivityDTO;
import cz.muni.fi.pa165.moduledataaccess.service.ActivityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("activity")
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("all")
    public ResponseEntity<List<Activity>> getAll() {
        return ResponseEntity.ok(activityService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Activity> getById(@PathVariable long id) {
        return ResponseEntity.of(activityService.getById(id));
    }

    @PostMapping
    public void create(@Valid @RequestBody ActivityDTO activity) {
        activityService.create(new Activity(0, activity.name()));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        activityService.delete(id);
    }

    @PutMapping
    public void update(@Valid @RequestBody Activity activity) {
        activityService.update(activity);
    }
}
