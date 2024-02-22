package popopz.popopzlearn1.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static popopz.popopzlearn1.utils.LogUtil.log;

public class ChatUtil {
    static String prefix = "<red>[</red><blue>PopzLearn</blue><red>]</red> ";

    public static void sendMsg(CommandSender sender, String string) {
        Component msg = MiniMessage.miniMessage().deserialize(prefix.concat(string));

        if (sender instanceof ConsoleCommandSender) log(string);
        else if (sender instanceof Player) sender.sendMessage(msg);
    }
    public static void sendMsg(CommandSender sender, Component text) {
        if (sender instanceof ConsoleCommandSender) log(prefix.concat(text.toString()));
        else if (sender instanceof Player) sender.sendMessage(text);
    }
    public static void sendMsg(CommandSender sender, String string, boolean showPrefix) {
        Component msg = MiniMessage.miniMessage().deserialize((showPrefix)?prefix.concat(string):string);

        if (sender instanceof ConsoleCommandSender) log(string);
        else if (sender instanceof Player) sender.sendMessage(msg);
    }
    public static void sendMsg(String level, CommandSender sender, String string) {
        Component msg = MiniMessage.miniMessage().deserialize(prefix.concat(string));

        if (sender instanceof ConsoleCommandSender) log(level, string);
        else if (sender instanceof Player) sender.sendMessage(msg);
    }
}
