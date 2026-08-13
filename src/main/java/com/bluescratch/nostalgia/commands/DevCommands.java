package com.bluescratch.nostalgia.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class DevCommands {
    public static LiteralArgumentBuilder<CommandSourceStack> register() {
        return literal("dev")
                    .then(literal("popup")
                        .then(literal("show")
                                .then(argument("players", EntityArgument.players())
                                        .then(argument("title", StringArgumentType.string())
                                                .then(argument("text", StringArgumentType.string())
                                                        .then(argument("minimize", BoolArgumentType.bool())
                                                                .executes(ModCommands::popup)
                                                        )
                                                )
                                        )
                                )
                        )

                    );
    }
}
