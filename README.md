# CMB Simulator GUI:
This program will allow to simulate the execution of cmb files layer by layer.
Simply, open a CMB file and select a layer, then using the command slider move up and down to visually see commads execution.
## Currently selecting a model is done manually through the MainInterface class:
```
private void initUI() {

        String target = "samples/CMB_File_Name.cmb";
        ....
```
![cmb_parser_ui](https://user-images.githubusercontent.com/2373344/181518742-bb48865b-56ac-42d2-b542-0bba0acaf66c.png)

## Note: The [CMB Decoder](https://github.com/hkof/cmb_decoder) library (.jar) is required in order for the simulator to run.