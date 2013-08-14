
    2013 (C) Alex Dobrianski Satellite encryption communication
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>
    Design and development by Team "Plan B" is licensed under 
    a Creative Commons Attribution-ShareAlike 3.0 Unported License.
    http://creativecommons.org/licenses/by-sa/3.0/ 

                                   Preamble.

Encryption of a data is essential part of a communication especially in space.

Current encryption methods uses different encryption algorithms such as RSA, 
AES, and etc. That algorithms based on a small key which allows encrypt all 
communication session, and assumption that communication will be secure as long 
as security measure will keep small key secret.

Current project utilize old (instead of modern) encryption technique: 
"Encryption notebook". Which with modern electronic data storage allow bypass 
limitation of a small size key.  As a source of random data for the big length, 
"Total" key, will be used processed, individually recorded, video stream (or any 
picture file). During communication used "key chunks" are not reusable.

Stage oh the development.

a)	Prototype for Windows based system. Prototype will include application 
        that will generate big encryption key from video data file, then will 
        use big key to encrypt any message and decrypt it. Encrypted message 
        can be send over regular communication channels. Control of the amount 
        of key's copies is in hands of a user.
b)	Android app (probably will be prohibited in store for security reasons)
        can be useful for SMS encryption.
c)	PIC processor based communication for satellite communication.
