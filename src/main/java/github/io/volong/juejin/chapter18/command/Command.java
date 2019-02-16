package github.io.volong.juejin.chapter18.command;

public interface Command {

    Byte LOGIN_REQUEST = 1;
    
    Byte LOGIN_RESPONSE = 2;
    
    Byte MESSAGE_REQUEST = 3;
    
    Byte MESSAGE_RESPONSE = 4;

    Byte CREATE_GROUP_REQUEST = 7;

    Byte CREATE_GROUP_RESPONSE = 8;

    Byte LIST_GROUP_MEMBERS_REQUEST = 9;

    Byte LIST_GROUP_MEMBERS_RESPONSE = 10;

    Byte JOIN_GROUP_REQUEST = 11;

    Byte JOIN_GROUP_RESPONSE = 12;

    Byte QUIT_GROUP_REQUEST = 13;

    Byte QUIT_GROUP_RESPONSE = 14;
}
