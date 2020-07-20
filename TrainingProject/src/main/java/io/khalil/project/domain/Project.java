package io.khalil.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project name is required.")
    private String projectName;

    @NotBlank(message = "Project Id is required.")
    @Size(min = 5 , message = "Please use at least 5 characters")
    @Column(updatable = false , unique = true )
    private String projectIdentifier;

    @NotBlank(message = "Please add description.")
    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date start_date;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date end_date ;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;
    @UpdateTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date modifiedAt;

    @One

}
