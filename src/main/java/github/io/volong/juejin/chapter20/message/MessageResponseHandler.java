package github.io.volong.juejin.chapter20.message;

import github.io.volong.juejin.chapter20.packet.Packet;
import github.io.volong.juejin.chapter20.command.Command;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        String fromUserId = msg.getFromUserId();
        String fromUserName = msg.getFromUserName();

        System.out.println(fromUserId + "-" + fromUserName + " : " + msg.getMessage());
    }

    public static class LoginResponsePacket extends Packet {

        private String userId;

        private String userName;

        private boolean success;

        private String reason;

        public LoginResponsePacket() {
        }

        @Override
        public Byte getCommand() {
            return Command.LOGIN_RESPONSE;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof LoginResponsePacket)) return false;
            final LoginResponsePacket other = (LoginResponsePacket) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$userId = this.getUserId();
            final Object other$userId = other.getUserId();
            if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) {
                return false;
            }
            final Object this$userName = this.getUserName();
            final Object other$userName = other.getUserName();
            if (this$userName == null ? other$userName != null : !this$userName.equals(other$userName)) {
                return false;
            }
            if (this.isSuccess() != other.isSuccess()) return false;
            final Object this$reason = this.getReason();
            final Object other$reason = other.getReason();
            if (this$reason == null ? other$reason != null : !this$reason.equals(other$reason)) {
                return false;
            }
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof LoginResponsePacket;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $userId = this.getUserId();
            result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
            final Object $userName = this.getUserName();
            result = result * PRIME + ($userName == null ? 43 : $userName.hashCode());
            result = result * PRIME + (this.isSuccess() ? 79 : 97);
            final Object $reason = this.getReason();
            result = result * PRIME + ($reason == null ? 43 : $reason.hashCode());
            return result;
        }

        public String toString() {
            return "LoginResponsePacket(userId=" + this.getUserId() + ", userName=" + this.getUserName() + ", success=" + this.isSuccess() + ", reason=" + this.getReason() + ")";
        }
    }
}