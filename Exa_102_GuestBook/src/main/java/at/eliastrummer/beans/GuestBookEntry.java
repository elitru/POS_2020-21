/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.eliastrummer.beans;

import java.io.Serializable;

/**
 *
 * @author root
 */
public class GuestBookEntry implements Serializable {
    private String nickname;
    private String email;
    private String command;
    
    public GuestBookEntry() { }

    public GuestBookEntry(String nickname, String email, String command) {
        this.nickname = nickname;
        this.email = email;
        this.command = command;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "GuestBookEntry{" + "nickname=" + nickname + ", email=" + email + ", command=" + command + '}';
    }
}
