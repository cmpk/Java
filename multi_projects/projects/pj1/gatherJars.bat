REM é¿çsópÇ… jar ÇèWÇﬂÇÈ
@set TARGET=libs
@set BUILD_DIR=build\libs

@if exist %TARGET% (
    rmdir %TARGET% /S /q
)
@mkdir %TARGET%
@xcopy ..\common\%BUILD_DIR% %TARGET%
@xcopy ..\pj2\%BUILD_DIR% %TARGET%
@xcopy build\libs %TARGET%
