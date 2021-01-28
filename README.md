<h1>Card Tournament</h1>
This is a simple project for subject Basics of Java Programming at my University. </br>
Let me introduce you to main assumptions of this project.</br>

We have single game:
<ol>
    <li>We draw entire deck evenly to both players.</li>
    <li>Every player counts numeric value of his cards from given schema: </br>
        2 - 2p, 3 - 3p, ..., 10 - 10p, Jack - 11p, Queen - 12p, King - 13p, Ace - 14p</li>
    <li>Player with higher sum of points wins</li>
    <li>In case of the same sum of points, the player who have Ace of Hearts wins the game.</li>
</ol>

<h2>Tournament</h2>
<ol>
    <li>Tournament has 1024 participants.</li>
    <li>In every round players are drawn in pairs randomly.</br>
        Both players play against each other. The player who win goes to the next round.</li>
    <li>All games are played simultaneously (every game in separate thread).</li>
    <li>Player, who won last round gets 1st place. Player who lose gets 2nd place.</br>
        Both players who lost in previous round gets 3rd place ex aequo.</li>
    <li>Final game is printed on the screen</li>
    <li>Data after first round is saved to generate statistics at the end of tournament.</li>
</ol>

<h2>Participants</h2>
<ol>
    <li>List of all players is readed from a txt file with csv data.</li>
</ol>

<h2>After Tournament</h2>
<ol>
    <li>For those players, who achieved places from 1 to 3 are sending congratulatory letter
        which are personalized. Every letter is saved to .txt file and name of the file is name and surname of a player.</li>
    <li>Following statistics are generated: 
        <ol>
        <li>Average score by players from each voivodeship.</li>
        <li>Median of points gained by men and women.</li>
        <li>Max and min score in stage one winners.</li>
        <li>Sum of points gainded by participants in following age categories:
            (0 - 18), [18 - 30), [30 - 55), [55, 100).</li>
        </ol>
        </li>
</ol>
