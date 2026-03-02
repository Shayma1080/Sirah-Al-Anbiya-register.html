package org.intecbrussel.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.intecbrussel.model.UserProgress;

import java.util.List;

@Data
@Getter
@Setter
public class UserProgressDTO {

    private Long userId;
    private String username;

    private Long prophetId;
    private String prophetName;

    private int progressPercentage;

    public static UserProgressDTO mapToDTO(UserProgress progress){

        UserProgressDTO dto = new UserProgressDTO();

        dto.setUserId(progress.getUser().getId());
        dto.setUsername(progress.getUser().getUsername());

        dto.setProphetId(progress.getProphet().getId());
        dto.setProphetName(progress.getProphet().getName());

        dto.setProgressPercentage(progress.getProgressPercentage());

        return dto;
    }
}
