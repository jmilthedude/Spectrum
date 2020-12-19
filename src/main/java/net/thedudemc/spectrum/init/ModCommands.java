package net.thedudemc.spectrum.init;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.thedudemc.spectrum.Spectrum;
import net.thedudemc.spectrum.command.Command;

import java.util.function.Supplier;

import static net.minecraft.command.Commands.literal;

public class ModCommands {

//    public static ReloadConfigsCommand RELOAD_CONFIGS;


    public static void registerCommands(CommandDispatcher<CommandSource> dispatcher, Commands.EnvironmentType env) {
//        RELOAD_CONFIGS = registerCommand(ReloadConfigsCommand::new, dispatcher, env);

    }

    public static <T extends Command> T registerCommand(Supplier<T> supplier, CommandDispatcher<CommandSource> dispatcher, Commands.EnvironmentType env) {
        T command = supplier.get();

        if (!command.isDedicatedServerOnly() || env == Commands.EnvironmentType.DEDICATED || env == Commands.EnvironmentType.ALL) {
            LiteralArgumentBuilder<CommandSource> builder = literal(command.getName());
            builder.requires((sender) -> sender.hasPermissionLevel(command.getRequiredPermissionLevel()));
            command.build(builder);
            dispatcher.register(literal(Spectrum.MOD_ID).then(builder));
        }

        return command;
    }

}
