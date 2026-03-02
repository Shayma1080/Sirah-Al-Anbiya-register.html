package org.intecbrussel.controller;

import lombok.RequiredArgsConstructor;
import org.intecbrussel.dto.UserProgressDTO;
import org.intecbrussel.model.UserProgress;
import org.intecbrussel.service.UserProgressService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserProgressController {

    private final UserProgressService userProgressService;

    @PostMapping
    public UserProgressDTO updateProgress(
            @RequestParam Long userId,
            @RequestParam Long prophetId,
            @RequestParam int progressPercentage){

        UserProgress progress =
                userProgressService.updateProgress(userId, prophetId, progressPercentage);

        return mapToDTO(progress);
    }

    private UserProgressDTO mapToDTO(UserProgress progress){

        UserProgressDTO dto = new UserProgressDTO();

        dto.setUserId(progress.getUser().getId());
        dto.setUsername(progress.getUser().getUsername());

        dto.setProphetId(progress.getProphet().getId());
        dto.setProphetName(progress.getProphet().getName());

        dto.setProgressPercentage(progress.getProgressPercentage());

        return dto;
    }

    @GetMapping
    public UserProgressDTO getProgress(
            @RequestParam Long userId,
            @RequestParam Long prophetId){

        UserProgress progress =
                userProgressService.getProgress(userId, prophetId)
                        .orElseGet(() ->
                                userProgressService.updateProgress(userId, prophetId, 0)
                        );

        return UserProgressDTO.mapToDTO(progress);
    }


}
