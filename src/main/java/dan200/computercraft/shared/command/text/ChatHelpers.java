/*
 * This file is part of ComputerCraft - http://www.computercraft.info
 * Copyright Daniel Ratcliffe, 2011-2021. Do not distribute without permission.
 * Send enquiries to dratcliffe@gmail.com
 */
package dan200.computercraft.shared.command.text;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.*;

/**
 * Various helpers for building chat messages.
 */
public final class ChatHelpers
{
    private static final ChatFormatting HEADER = ChatFormatting.LIGHT_PURPLE;

    private ChatHelpers() {}

    public static MutableComponent coloured( String text, ChatFormatting colour )
    {
        MutableComponent component = new TextComponent( text == null ? "" : text );
        component.setStyle( component.getStyle().withColor( colour ) );
        return component;
    }

    public static <T extends MutableComponent> T coloured( T component, ChatFormatting colour )
    {
        component.setStyle( component.getStyle().withColor( colour ) );
        return component;
    }

    public static MutableComponent text( String text )
    {
        return new TextComponent( text == null ? "" : text );
    }

    public static MutableComponent translate( String text )
    {
        return new TranslatableComponent( text == null ? "" : text );
    }

    public static MutableComponent translate( String text, Object... args )
    {
        return new TranslatableComponent( text == null ? "" : text, args );
    }

    public static MutableComponent list( MutableComponent... children )
    {
        MutableComponent component = new TextComponent( "" );
        for( MutableComponent child : children )
        {
            component.append( child );
        }
        return component;
    }

    public static MutableComponent position( BlockPos pos )
    {
        if( pos == null ) return translate( "commands.computercraft.generic.no_position" );
        return translate( "commands.computercraft.generic.position", pos.getX(), pos.getY(), pos.getZ() );
    }

    public static MutableComponent bool( boolean value )
    {
        return value
            ? coloured( translate( "commands.computercraft.generic.yes" ), ChatFormatting.GREEN )
            : coloured( translate( "commands.computercraft.generic.no" ), ChatFormatting.RED );
    }

    public static MutableComponent link( MutableComponent component, String command, MutableComponent toolTip )
    {
        return link( component, new ClickEvent( ClickEvent.Action.RUN_COMMAND, command ), toolTip );
    }

    public static MutableComponent link( MutableComponent component, ClickEvent click, MutableComponent toolTip )
    {
        Style style = component.getStyle();

        if( style.getColor() == null ) style = style.withColor( ChatFormatting.YELLOW );
        style = style.withClickEvent( click );
        style = style.withHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, toolTip ) );

        component.setStyle( style );

        return component;
    }

    public static MutableComponent header( String text )
    {
        return coloured( text, HEADER );
    }

    public static MutableComponent copy( String text )
    {
        TextComponent name = new TextComponent( text );
        name.setStyle( name.getStyle()
            .withClickEvent( new ClickEvent( ClickEvent.Action.COPY_TO_CLIPBOARD, text ) )
            .withHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new TranslatableComponent( "gui.computercraft.tooltip.copy" ) ) ) );
        return name;
    }
}
