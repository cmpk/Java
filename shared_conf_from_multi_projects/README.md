*������*

# �ۑ�
log4j 2 �̐ݒ�t�@�C���ɂ��āAeclipse������s����ۂ̓N���X�p�X�Ɋi�[�t�H���_���w�肷�邱�ƂŎ��s�ł����B  

��build.gradle��  
```
dependencies {
   ...
    // �ݒ�t�@�C���̈ʒu���N���X�p�X�Ƃ��Ēǉ�
    runtime files('../../conf')
   ...
}
```

�������Ajar ������s����ƁA�N���X�p�X��ǉ����Ă��Ă��ݒ�t�@�C���������邱�Ƃ��ł��Ȃ������B  

��build.gradle��
```
jar {
    manifest {
        attributes 'Main-Class': 'pj1.Main'

        // �ˑ����C�u�������N���X�p�X�Ɏw��
        attributes 'Class-Path': configurations.runtime.collect{it.name.endsWith('jar') ? it.name : '../../' + it.name}.join(' ')
    }
    ...

```
�� �_��

�����s���N���X�p�X�w�聄
```
java -cp ..\..\conf -jar ...
```
�� �_��


���̂��߁Ajava�\�[�X�R�[�h���Œ��ڃt�@�C�����w�肵���B  


�Ȃ��A���s���I�v�V�����w��ł͉\�B  
```
java -Dlog4j.configurationFile=..\..\conf\log4j2.xml -jar ...
```
