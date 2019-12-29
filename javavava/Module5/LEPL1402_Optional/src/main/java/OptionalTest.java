import java.util.Optional;

public class OptionalTest {

    /**
     * return an Optional<TeamLeader> object from teamLeader
     */
    public Optional<TeamLeader> createOptionalTeamLeader(TeamLeader teamLeader){
        return Optional.ofNullable(teamLeader);
    }

    /**
     * Increment by one the age of teamLeader
     */
    public void incAge(Optional<TeamLeader> optionalTeamLeader){
        if (optionalTeamLeader.isPresent()) {
            optionalTeamLeader.get().setAge(optionalTeamLeader.get().getAge() + 1);
        }
    }

    /**
     * Increment by one the age of teamLeader if its age is > 15
     */
    public void incAgeIfMoreThanFifteen(Optional<TeamLeader> optionalTeamLeader){
        if (optionalTeamLeader.isPresent()) {
            optionalTeamLeader.get().setAge( (optionalTeamLeader.get().getAge()>15) ?
                    optionalTeamLeader.get().getAge()+1 : optionalTeamLeader.get().getAge() );
        }
    }

    /**
     * return the name of teamLeader or "No team leader"
     */
    public String getName(Optional<TeamLeader> optionalTeamLeader){
        return (optionalTeamLeader.isPresent()) ? optionalTeamLeader.get().getName(): "No team leader";
    }

    /**
     * return the name of the teamLeader of the team of person or "No team leader"
     */
    public String getNameOfTeamLeader(Optional<Person> optionalPerson){
        return (optionalPerson.get().getTeam().get().getTeamLeader().isPresent()) ?
                optionalPerson.get().getTeam().get().getTeamLeader().get().getName() : "No team leader";
    }
}
