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
            $logger.trace "user : {},{}.", message.user[:name], message.user[:screen_name]

            # media_url を抜き出して、長さ 4 の配列を作成する。
            urls = message.entity.select { |e|
                e.has_key? :media_url
            }.map{ |e|
                e[:media_url]
            }

            urls.fill(0,4) { |i|
                unless urls[i] == nil
                    urls[i]
                else
                    ""
                end
            }
            $controller.add_message(message, urls)
        end
    end

    # アイコン設定
    on_gui_window_change_icon do |window, icon|
        $logger.debug "start on_gui_window_change_icon in javafx."
        $controller.set_icon icon
        $logger.debug "end on_gui_window_change_icon in javafx."
    end
end
