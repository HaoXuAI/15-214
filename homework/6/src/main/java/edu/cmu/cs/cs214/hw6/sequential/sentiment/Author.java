package edu.cmu.cs.cs214.hw6.sequential.sentiment;

/**
 * Small immutable class to represent authors of a commit
 * with a name and an email address.
 */
public class Author {

    private final String name;
    private final String email;

    public Author(String name, String email) {
        assert (name != null);
        this.name = name;
        if (email == null)
            this.email = "";
        else
            this.email = email;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Author) {
            return this.name.equals(((Author) obj).name) &&
                    this.email.equals(((Author) obj).email);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 17 + email.hashCode();
    }

    @Override
    public String toString() {
        return name + " <" + email + ">";
    }
}
