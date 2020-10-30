#
function Select-Winner($Team1,$Team2)
{
    If ($Team1 -eq "Legia")
    {
        return $Team2
    }
    return $Team1
}
