# ViteList

Whitelisting of you minecraft servers made easy!

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Installation

Installing is as easy as it gets:

1. Stop the server
2. Drag and drop the plugin in your velocity plugins folder
3. Start the server, done!
4. If you want to use MySQL just change it in the config and restart

## Usage

You can see a list of commands and permissions below. parameters covered with `()` are optional parameters
meaning they can be ignored, and the ones that are covered with `<>` are required parameters meaning
you should feed those parameters

1. Commands:
    * **/WhiteList on `(serverName)`** - Enables whitelist globally or per server
    * **/WhiteList off `(serverName)`** - Disables whitelist globally or per server
    * **/WhiteList add `<playerName>` `(serverName)`** - Adds a certain player to whitelist globally or per server
    * **/WhiteList remove `<playerName>` `(serverName)`** - Removes a certain player to whitelist globally or per server
2. Permissions:
    * **vitelist.commands.on**
    * **vitelist.commands.off**
    * **vitelist.commands.add**
    * **vitelist.commands.remove**

## Contributing

Contributions are welcome for anything ranging from:

- Reporting bugs
- Suggesting enhancements
- Submitting pull requests
- Coding conventions or style guides

## License

This project is licensed under the [GPL-3.0 license](LICENSE.md).