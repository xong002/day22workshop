package sg.nus.iss.day22workshop.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RSVP {
    private int id;
    private String fullName;
    private String email;
    private Integer phone;
    private Date confirmationDate;
    private String comments;
}
