//1:int
let
    f:()()int = fun -> fun -> 1 end end
in
    println f()()
end;;
