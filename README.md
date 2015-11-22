MikutterJFX
===========

Usage:
------


Requirements:
-------------

- Java8

Install:
--------

```sh
git clone https://github.com/mikoto2000/MikutterJFX.git

cd MikutterJFX
./gradlew copyDependencies
git clone git://toshia.dip.jp/mikutter.git src/main/resource/script/mikutter
git submodule init
git submodule sync
git submodule update

# bundler インストール
java -jar dependencies/jruby-complete-9.0.3.0.jar -S gem install -i src/main/resource/gems/vendor/gem_home --no-rdoc --no-ri bundler

#### mikutter 設定開始
cd src/main/resource/script/mikutter/

# TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET を自分で用意したものに修正する。
vim core/config.rb

### plugin 設定開始
cd core/plugin/

# 不要なプラグインを削除
rm -rf achievement activity alsa aspectframe bitly bugreport change_account command console direct_message display_requirements extract followingcontrol gtk home_timeline image_file_cache libnotify list list_for_profile list_settings mentions notify openimg photo_support profile proxy ratelimit saved_search search set_input set_view settings shortcutkey skin smartthread sound user_filesystem_cache

# bundler で gem をインストール
cd ../..
GEM_HOME=src/main/resource/gems/vendor/gem_home GEM_PATH=../../../../../src/main/resource/gems/vendor/gem_home java -jar ../../../../../dependencies/jruby-complete-9.0.3.0.jar -S bundle install --path=../../gems/vendor/

# 実行
cd ../../../../..
./gradlew build
java -cp "./build/libs/MikutterJFX.jar;./dependencies/jruby-complete-9.0.3.0.jar;./dependencies/slf4j-api-1.7.12.jar;./dependencies/logback-core-1.1.3.jar;./dependencies/logback-classic-1.1.3.jar;./src/main/resource" jp.dip.oyasirazu.mikutterj.MikutterJFX
```

License:
--------

Copyright (C) 2015 mikoto2000

This software is released under the MIT License, see LICENSE

このソフトウェアは MIT ライセンスの下で公開されています。 LICENSE を参照してください。

Author:
-------

mikoto2000 <mikoto2000@gmail.com>

