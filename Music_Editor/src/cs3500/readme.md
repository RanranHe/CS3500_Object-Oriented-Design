#MusicEditor说明文档
[toc]
##修改说明

###打印文本实现相关方法
`MusicEditorImpl`
- 增加PitchMap，用于保存note中存在过的音阶；
- 修改addNote方法，保存音阶到PitchMap；
- 修改extendBoard方法,直接添加缺少的beat到this.music；

`ConsoleView`
- 修改renderStaff方法，通过MusicEditorImpl.getPitchMap方法过滤无内容的音阶。
- 修改render方法，按新的方式拼接字符；
- 重写renderNotes方法，实现beat行的字符拼接；
- 新增isFirst方法，用于实现近似音阶开始位置的重置功能（可废弃，采用全音阶则无需这个方法）；
- 新增getSpace方式，实现空白字符模板；
- 新增n2s方法，实现等距行号的拼接；
- 新增saveToFile方法，保存拼接的字符串为文本；

`AbstractView`
- 修改toString方法，去除空白字符（这个尽量不要手工判断，应该用程序逻辑实现偏移计算）；

###播放midi相关实现
`MidiViewImpl`
- 采用直接播放model的方式，而不是播放文件。

`ANote`
- 添加pitch记录原始音阶值
- 添加getOldPitch方法获取原始音阶值

`Note`
- 添加pitch记录原始音阶值
- 修改Note构造方法，设置私有变量，否则midiValue方法按之前定义的类属性会发生异常；