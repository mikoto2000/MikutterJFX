# -*- coding: utf-8 -*-
require 'java'

Plugin.create(:javafx) do
    on_boot do
        $logger.debug "start on_boot in javafx."
        $logger.debug "end on_boot in javafx."
    end

    on_update do |service, messages|
        sorted_message = messages.sort_by{ |item| item['timestamp'] }
        sorted_message.each do |message|
            message_body = message.body.gsub(/\n/, "\n\t")
            $logger.trace "add message 【{}】{}.", message.user, message_body
            $controller.add_message message.user[:profile_image_url], message.user.to_s, message_body
        end
    end

    # アイコン設定
    on_gui_window_change_icon do |window, icon|
        $logger.debug "start on_gui_window_change_icon in javafx."
        $controller.set_icon icon
        $logger.debug "end on_gui_window_change_icon in javafx."
    end
end
