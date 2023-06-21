package sg.nus.iss.day22workshop.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.nus.iss.day22workshop.model.RSVP;

@Repository
public class RSVPRepo {

    @Autowired
    JdbcTemplate template;

    private final String countSQL = "select count(*) from rsvp";
    private final String findAllSQL = "select * from rsvp";
    private final String findByIdSQL = "select * from rsvp where id = ?";
    private final String insertSQL = "insert into rspv (full_name, email, phone, confirmation_date, comments) values (?,?,?,?,?)";
    private final String updateSQL = "update rsvp set email = ?, phone = ?, confirmation_date = ? where id = ?";

    public int count() {
        int iResult = template.queryForObject(countSQL, Integer.class);
        return iResult;
    }

    public List<RSVP> findAll() {
        List<RSVP> rsvps = new ArrayList<>();
        rsvps = template.queryForList(findAllSQL, RSVP.class);
        return rsvps;
    }

    public RSVP findById(int id) {
        RSVP rsvp = new RSVP();
        rsvp = template.queryForObject(findByIdSQL, BeanPropertyRowMapper.newInstance(RSVP.class), id);
        return rsvp;
    }

    public Boolean save(RSVP rsvp) {
        int iResult = 0;
        iResult = template.update(insertSQL, rsvp.getFullName(), rsvp.getEmail(), rsvp.getPhone(),
                rsvp.getConfirmationDate(), rsvp.getComments());
        return iResult > 0 ? true : false;
    }

    public Boolean update(RSVP rsvp) {
        int iResult = 0;
        iResult = template.update(updateSQL, rsvp.getEmail(), rsvp.getPhone(),
                rsvp.getConfirmationDate(), rsvp.getId());
        return iResult > 0 ? true : false;
    }

}
